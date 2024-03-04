package mz.org.csaude.metadata.clients

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import mz.org.csaude.metadata.date.DateUtils
import mz.org.csaude.metadata.drug.Product
import mz.org.csaude.metadata.restUtils.RestClient
import mz.org.csaude.metadata.server.Server
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

import java.text.ParseException
import java.text.SimpleDateFormat

@Transactional
@EnableScheduling
@Slf4j
class RestGetClientsCentralToolService {

    private static final NAME = "RestGetPharmaceuticalFormCentralToolService"


    private static final Logger LOGGER = LoggerFactory
            .getLogger("LOGGER");

    RestClient restClient = new RestClient()
    private static final String FORMAT_STRING = '| %1$-10s |  %2$-40s|  %3$-30s|';


    static lazyInit = false

    @Scheduled(cron = "0 0 0 1 * ?")
    void execute() {
        def offset = 0
        def count

        while (true) {
            count = loadClientsFromCentralTool(offset).size()

            if (count > 0) {
                offset++
                print(offset)

            } else {
                break // Exit the loop when count becomes zero
            }
        }
    }

    @Transactional
    List<Client>  loadClientsFromCentralTool(int offset) {
        Client.withTransaction {
            def clientList = new ArrayList<Client>()
                String urlPath = "/api/v1/clients/types?size=100&page=" + offset;
                LOGGER.info("Iniciando a Busca de Clientes")
                def response = restClient.requestGetDataOnProvincialServerClient(urlPath)
            if(response.getAt('authenticated') == null) {
                LOGGER.info("Fim da Busca de Clientes")
                return clientList;
            }
            for (def clientObject : response) {
                try {
                    def clientExist = Client.findWhere(code: clientObject.getAt("code"))

                    if (!clientExist) {
                        clientExist = new Client()
                        clientExist.id =UUID.randomUUID()
                        clientExist.status = clientObject.getAt(("status"))
                        clientExist.updateDate = DateUtils.createDate(clientObject.getAt(("updateDate")),'yyyy-MM-dd')
                        clientExist.code = clientObject.getAt(("code"))
                        clientExist.description = clientObject.getAt(("description"))
                        clientExist.nationalCode = clientObject.getAt(("nationalCode")) != null ? clientObject.getAt(("nationalCode")) as int : null
                        clientExist.name = clientObject.getAt(("name"))
                        clientExist.address = clientObject.getAt(("address"))
                        clientExist.phone = clientObject.getAt(("phone"))
                        clientExist.indicator = clientObject.getAt(("indicator")) as boolean
                        clientExist.clientTypeCode = clientObject.getAt(("clientTypeCode"))
                        clientExist.prescriptionLevelCode = clientObject.getAt(("prescriptionLevelCode"))
                        clientExist.prescriptionLevelDescription = clientObject.getAt(("prescriptionLevelDescription"))
                        clientExist.warehouseCode = clientObject.getAt(("warehouseCode"))
                        clientExist.warehouseCodeName = clientObject.getAt(("warehouseCodeName"))
                        clientExist.unitTypeCode = clientObject.getAt(("unitTypeCode"))
                        clientExist.unitTypeDescription = clientObject.getAt(("unitTypeDescription"))
                        clientExist.provinceCode = clientObject.getAt(("provinceCode"))
                        clientExist.provinceDescription = clientObject.getAt(("provinceDescription"))
                        clientExist.districtCode = clientObject.getAt(("districtCode"))
                        clientExist.districtDescription = clientObject.getAt(("districtDescription"))
                        clientExist.startDate = clientObject.getAt(("startDate"))
                        clientExist.validate()
                        clientExist.save()
                    }
                    clientList.add(clientExist)
                } catch (Exception e) {
                    e.printStackTrace()
                } catch (ConnectException e1) {
                    e1.printStackTrace()
                } finally {
                    continue
                }

            }

     return clientList
        }
    }





}
