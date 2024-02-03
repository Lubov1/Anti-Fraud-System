package antifraud.rest;

import antifraud.rest.Dao.Result;
import antifraud.rest.Dao.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @PostMapping(value = "/api/antifraud/transaction", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ObjectNode sendInvite(RequestEntity<Status> status) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        Status s= status.getBody();

        if (status.getBody().getAmount() <= 0) {
            throw new RuntimeException();
        }
        if (status.getBody().getAmount() <= 200) {
            node.put("result", Result.ALLOWED.toString());
        } else if(status.getBody().getAmount() <= 1500) {
            node.put("result", Result.MANUAL_PROCESSING.toString());
        } else {
            node.put("result", Result.PROHIBITED.toString());
        }
        return node;

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<JsonNode> handleException(Exception e) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }


}
