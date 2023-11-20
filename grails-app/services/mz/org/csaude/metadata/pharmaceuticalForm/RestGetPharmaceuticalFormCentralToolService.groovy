package mz.org.csaude.metadata.pharmaceuticalForm

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import mz.org.csaude.metadata.clients.Client
import mz.org.csaude.metadata.date.DateUtils
import mz.org.csaude.metadata.drug.Product
import mz.org.csaude.metadata.restUtils.RestClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Transactional
@EnableScheduling
@Slf4j
class RestGetPharmaceuticalFormCentralToolService {

    private static final NAME = "RestGetPharmaceuticalFormCentralToolService"


    private static final Logger LOGGER = LoggerFactory
            .getLogger("RestGetPharmaceuticalFormCentralToolService");


 //   final String urlPath = 'http://10.1.1.13:8099/'
    RestClient restClient = new RestClient()
    private static final String FORMAT_STRING = '| %1$-10s |  %2$-40s|  %3$-30s|';

    private static final String MESSAGE = String.format(
            FORMAT_STRING,
            "Id Dispensa",
            "Nome",
            "NID");

    static lazyInit = false

    //@Scheduled(fixedDelay = 60000L)
    void execute() {
        def offset = 0
        def count

        while (true) {
            count = loadPharmaceuticalFromCentralTool(offset).size()

            if (count > 0) {
                offset++
                print(offset)

            } else {
                break // Exit the loop when count becomes zero
            }
        }
    }


    @Transactional
    List<PharmaceuticalForm> loadPharmaceuticalFromCentralTool(int offset) {
        PharmaceuticalForm.withTransaction {
            def pharmaceuticalForm = new ArrayList<PharmaceuticalForm>()
                String urlPath = "/api/v1/meta/pharmaceutical-forms?size=100&page=" + offset;
                LOGGER.info("Iniciando a Busca de Forma Farmaceutica")
                def response = restClient.requestGetDataOnProvincialServerClient(urlPath)
                LOGGER.info(MESSAGE)
            if(response.size() == 0) {
                return pharmaceuticalForm;
            }
            for (def pharmaceuticalFormObject : response) {
                try {
                    def formExist = PharmaceuticalForm.findWhere(code: pharmaceuticalFormObject.getAt("code"))

                    if (!formExist) {
                        formExist = new PharmaceuticalForm()
                        formExist.id =UUID.randomUUID()
                        formExist.status = pharmaceuticalFormObject.getAt(("status"))
                        formExist.updateDate = DateUtils.createDate(pharmaceuticalFormObject.getAt(("updateDate")),'yyyy-MM-dd')
                        formExist.code = pharmaceuticalFormObject.getAt(("code"))
                        formExist.description = pharmaceuticalFormObject.getAt(("description"))
                        formExist.shortDescription  = pharmaceuticalFormObject.getAt(("shortDescription"))
                        formExist.validate()
                        formExist.save()
                    }
                    pharmaceuticalForm.add(formExist)
                } catch (Exception e) {
                    e.printStackTrace()
                } catch (ConnectException e1) {
                    e1.printStackTrace()
                } finally {
                    continue
                }

            }
            LOGGER.info("Termino do carregamento Forma Farmaceutica")
         return pharmaceuticalForm

        }
    }




}
