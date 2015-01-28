package flashGetter.view;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ViewEventDispatcher {
    
    private List<ViewHandler> listeners;
    
    public static class InnerClass{
        
        public static final ViewEventDispatcher instance = new ViewEventDispatcher();
        
    }
    
    private ViewEventDispatcher() {
        listeners = new ArrayList<ViewHandler>();
    }
    
    public void register(ViewHandler handler){
        listeners.add(handler);
    }
    
    public void fireEvent(ViewEvent event){
        
        if(event == null) return;
        
            listeners.stream()
            .filter(target -> target.getGroupClass().isAssignableFrom(event.getTarget()))
            .forEach(listener -> listener.invoke(event));;
    }

}