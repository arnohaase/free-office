package de.arnohaase.freeoffice.calc.backend;


/**
 * This is a 'semi-internal' interface that is intended to be used only by command implementations. It allows
 *  modifications of back end state, and access to it must therefore be secured against concurrency issues.<br>
 *  
 * The idea is that a CalcBackend implementation accepts a command, executing it at its leisure in whatever
 *  threading context it deems appropriate.
 * 
 * @author arno
 */
public interface MutableCalcBackend extends CalcBackend {

}
