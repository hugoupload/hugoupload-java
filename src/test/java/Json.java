import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugocut.model.InitData;


public class Json {
    public static void ParseJson() throws JsonProcessingException {
        InitData id = new InitData();
        ObjectMapper mapper = new ObjectMapper();
        String out = mapper.writeValueAsString(id);
        System.out.println(out);
    }
}
