package mz.org.csaude.metadata.drug

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import mz.org.csaude.metadata.clients.Client
import mz.org.csaude.metadata.date.DateUtils
import mz.org.csaude.metadata.date.DrugIdHelper
import mz.org.csaude.metadata.restUtils.RestClient
import mz.org.csaude.metadata.therapeuticRegimen.Regimen
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Transactional
@EnableScheduling
@Slf4j
class RestGetProductCentralToolService {

    private static final NAME = "RestGetProductCentralToolService"


    private static final Logger LOGGER = LoggerFactory
            .getLogger("RestGetProductCentralToolService");


    RestClient restClient = new RestClient()
    private static final String FORMAT_STRING = '| %1$-10s |  %2$-40s|  %3$-30s|';

    private static final String MESSAGE = String.format(
            FORMAT_STRING,
            "Id Dispensa",
            "Nome",
            "NID");

    static lazyInit = false

 @Scheduled(fixedDelay = 60000L)
 void execute() {
     /*
     def offset = 0
     def count = loadDrugsFromCentralTool(offset).size()

     if (count > 0) {
         offset = ++offset
         print(offset)
         loadDrugsFromCentralTool(offset)
     }
      */
     def offset = 0
     def count

     while (true) {
         count = loadDrugsFromCentralTool(offset).size()

         if (count > 0) {
             offset++
             print(offset)

         } else {
             break // Exit the loop when count becomes zero
         }
     }
 }


    @Transactional
    List<Product>  loadDrugsFromCentralTool(int offset) {
        Product.withTransaction {
                def productList = new ArrayList<Product>()
                String urlPath = "/api/v1/products?size=100&page=" + offset;
                LOGGER.info("Iniciando a Busca de Clientes")
                def response = restClient.requestGetDataOnProvincialServerClient(urlPath)
                LOGGER.info(MESSAGE)
            if(response.getAt('authenticated') == null) {
               return productList;
            }
            for (def productObject : response) {
                try {
                    def productExist = Product.findWhere(fnm: productObject.getAt("fnm"))

                    if (!productExist) {
                        productExist = new Product()
                        String fnmCode = productObject.getAt("fnm")

                        Object drug = DrugIdHelper.initiateDrugsList().get(  "'" + fnmCode + "'")
                        productExist.id = drug != null ? drug.id.replace("'", "") : UUID.randomUUID()
                        productExist.status = productObject.getAt(("status"))
                        productExist.updateDate = DateUtils.createDate(productObject.getAt(("updateDate")),'yyyy-MM-dd')
                        productExist.fnm = productObject.getAt(("fnm"))
                        productExist.description = productObject.getAt(("description"))
                        productExist.shortDescription = productObject.getAt(("shortDescription"))
                        productExist.fullDescription = productObject.getAt(("fullDescription"))
                        productExist.prioritary = productObject.getAt(("prioritary")) as boolean
                        productExist.kit = productObject.getAt(("kit")) as boolean
                        productExist.temperatureCode = productObject.getAt(("temperatureCode"))
                        productExist.temperatureDescription = productObject.getAt(("temperatureDescription"))
                        productExist.categoryCode = productObject.getAt(("categoryCode"))
                        productExist.categoryDescription = productObject.getAt(("categoryDescription"))
                        productExist.prescriptionLevelCode = productObject.getAt(("prescriptionLevelCode"))
                        productExist.prescriptionLevelDescription = productObject.getAt(("prescriptionLevelDescription"))
                        productExist.pharmaceuticFormCode = productObject.getAt(("pharmaceuticFormCode"))
                        productExist.pharmaceuticFormDescription = productObject.getAt(("pharmaceuticFormDescription"))
                        productExist.therapeuticClasseCode = productObject.getAt(("therapeuticClasseCode"))
                        productExist.therapeuticClasseDescription = productObject.getAt(("therapeuticClasseDescription"))
                        productExist.dosageCode = productObject.getAt(("dosageCode"))
                        productExist.dosageDescription = productObject.getAt(("dosageDescription"))
                        productExist.productUnitCode = productObject.getAt(("productUnitCode"))
                        productExist.productUnitDescription = productObject.getAt(("productUnitDescription"))
                        productExist.classificationCode = productObject.getAt(("classificationCode"))
                        productExist.classificationDescription = productObject.getAt(("classificationDescription"))
                        productExist.monthlyConsumption = productObject.getAt(("monthlyConsumption"))
                        productExist.volume = productObject.getAt(("volume"))
                        productExist.unitsPerPack = productObject.getAt(("unitsPerPack"))
                        productExist.managedByPack = productObject.getAt(("managedByPack"))
                        productExist.uuidOpenmrs =  drug != null ? drug.uuid_openmrs.replace("'", "") : null
                        productExist.validate()
                        productExist.save(flush:true)
                    }
                    def regimeList = new ArrayList()
                    regimeList = productObject.getAt("areas") as ArrayList
                    if (!regimeList.isEmpty()) {
                        for (def regimeTerapeutico in regimeList) {
                            def regimeExist = Regimen.findWhere(code: regimeTerapeutico.getAt("regimeCode"))
                            String regimeCode = regimeTerapeutico.getAt("regimeCode")
                            if (!regimeExist) {
                                println(DrugIdHelper.initiateRegimensList().get( "'" + regimeCode + "'"))
                                Object regimen = DrugIdHelper.initiateRegimensList().get("'" + regimeCode + "'")
                                regimeExist = new Regimen()
                                regimeExist.id = regimen != null ? regimen.id.replace("'", "") : UUID.randomUUID()
                                regimeExist.status = regimeTerapeutico.getAt(("status"))
                                regimeExist.updateDate = DateUtils.createDate(productObject.getAt(("updateDate")),'yyyy-MM-dd')
                                regimeExist.code = regimeTerapeutico.getAt(("regimeCode"))
                                regimeExist.description = regimeTerapeutico.getAt(("regimeDescription"))
                                regimeExist.areaCode = regimeTerapeutico.getAt(("areaCode"))
                                regimeExist.areaDescription = regimeTerapeutico.getAt(("areaDescription"))
                                regimeExist.categoryCode = regimeTerapeutico.getAt(("categoryCode"))
                                regimeExist.categoryDescription = regimeTerapeutico.getAt(("categoryDescription"))
                                regimeExist.uuidOpenmrs =  regimen != null ? regimen.openmrs_uuid.replace("'", "") : null
                            }
                            regimeExist.addToProducts(productExist)
                        //    productExist.addToTherapeuticRegimenList(regimeExist)
                        //    productExist.save(flush:true)
                            regimeExist.save(flush:true)
                        }
                    }
                    productList.add(productExist)
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
            return productList
        }
    }




}
