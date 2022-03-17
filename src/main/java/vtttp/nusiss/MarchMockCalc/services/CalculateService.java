package vtttp.nusiss.MarchMockCalc.services;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public String calculateResult(Integer oper1, String ops, Integer oper2){
        Integer result;
        switch(ops) {
            case "minus":
              // code block
              result = oper1 - oper2;
              break;
            case "plus":
              // code block
              result = oper1 + oper2;
              break;
              case "divide":
              result = oper1 / oper2;
              // code block
              break;
              case "multiply":
              result = oper1 * oper2;
              // code block
              break;
            default:
              // code block
              result = 0;
          }

        return result.toString();
    }
    
}
