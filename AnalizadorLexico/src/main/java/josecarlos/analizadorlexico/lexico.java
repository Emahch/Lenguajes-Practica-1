package josecarlos.analizadorlexico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author emahch
 */

public class lexico {
    private final Map<String, Map<Character, String>> transitions;
    private String currentState;
    
    public lexico () {
        // Definición de las transiciones del DFA
        transitions = new HashMap<>();
        transitions.put("q0", Map.of('a', "q1"));
        transitions.put("q1", Map.of('b', "q2"));
        transitions.put("q2", Map.of('a', "q1", 'b', "q2"));
        
        // Estado inicial
        currentState = "q0";
    }
    
    public String nextState(char input) {
        Map<Character, String> transition = transitions.get(currentState);
        if (transition != null && transition.containsKey(input)) {
            currentState = transition.get(input);
        } else {
            currentState = "error";  // Estado de error si no hay transición válida
        }
        return currentState;
    }
    
    public String getCurrentState() {
        return currentState;
    }
}
