
import com.kyungjoon.client.CellPhoneModel
import com.kyungjoon.client.CellPhoneRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class CellPhoneController(@Autowired val cellPhoneRepo: CellPhoneRepo) {

    @GetMapping("/cellPhone")
    fun getCount(): MutableList<CellPhoneModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return cellPhoneRepo.findAll(direction)
    }

    @PostMapping("/cellPhone")
    fun postClient(@RequestBody cellPhoneModel: CellPhoneModel): CellPhoneModel {
        return cellPhoneRepo.insert(cellPhoneModel)
    }



    @GetMapping("/cellPhone/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<CellPhoneModel> {
        return cellPhoneRepo.findById(id)
    }

    @DeleteMapping("/cellPhone/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        cellPhoneRepo.findById(id).let {
            cellPhoneRepo.deleteById(id)
        }
    }

    @DeleteMapping("/cellPhone/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        cellPhoneRepo.deleteAll()
    }


    @PatchMapping("/cellPhone/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody cellPhoneModel: CellPhoneModel): CellPhoneModel? {
        return cellPhoneRepo.findById(id).let {
            cellPhoneRepo.save(cellPhoneModel)
        }
    }

}
