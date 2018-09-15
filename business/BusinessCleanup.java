
package business;

import middleware.MiddlewareCleanup;
import middleware.externalinterfaces.Cleanup;


/**
 * @author  nngo2
 */
public class BusinessCleanup implements Cleanup {
    Cleanup mc;
    
    public void cleanup() {
        mc = new MiddlewareCleanup();
        mc.cleanup();
        
    }

}
