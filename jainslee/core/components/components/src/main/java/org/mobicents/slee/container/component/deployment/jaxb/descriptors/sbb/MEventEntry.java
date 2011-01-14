/**
 * Start time:12:50:56 2009-01-20<br>
 * Project: mobicents-jainslee-server-core<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">baranowb - Bartosz Baranowski
 *         </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
package org.mobicents.slee.container.component.deployment.jaxb.descriptors.sbb;

import java.util.EnumSet;

import javax.slee.EventTypeID;

import org.mobicents.slee.container.component.deployment.jaxb.slee.sbb.Event;
import org.mobicents.slee.container.component.deployment.jaxb.slee.sbb.InitialEventSelect;
import org.mobicents.slee.container.component.sbb.EventDirection;
import org.mobicents.slee.container.component.sbb.EventEntryDescriptor;
import org.mobicents.slee.container.component.sbb.InitialEventSelectVariable;

/**
 * Start time:12:50:56 2009-01-20<br>
 * Project: mobicents-jainslee-server-core<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">baranowb - Bartosz Baranowski
 *         </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class MEventEntry implements EventEntryDescriptor {

	private boolean initialEvent = false;
	private EventDirection eventDirection = null;
	private boolean maskOnAttach = false;
	private boolean lastInTransaction = true;
	private String description = null;
	private EventTypeID eventReference=null;
	
	//private ComponentKey eventReference = null;
	private String eventName = null;
	private EnumSet<InitialEventSelectVariable> initialEventSelects = EnumSet.noneOf(InitialEventSelectVariable.class);
	private String initialEventSelectorMethod = null;
	private String resourceOption = null;

	public MEventEntry(
			org.mobicents.slee.container.component.deployment.jaxb.slee11.sbb.Event llEvent) {
		super();
		
		this.eventDirection = EventDirection.fromString(llEvent
				.getEventDirection());
		this.initialEvent = Boolean.parseBoolean(llEvent.getInitialEvent());
		this.maskOnAttach = Boolean.parseBoolean(llEvent.getMaskOnAttach());

		// 1.1 last in transaction
		String v=llEvent.getLastInTransaction();
		if(v!=null && !Boolean.parseBoolean(v))
		{
			this.lastInTransaction=false;
		}
		
		this.description = llEvent.getDescription() == null ? null
				: llEvent.getDescription().getvalue();
				
		this.eventReference=new EventTypeID(llEvent.getEventTypeRef().getEventTypeName().getvalue(), llEvent.getEventTypeRef().getEventTypeVendor().getvalue(), llEvent.getEventTypeRef().getEventTypeVersion().getvalue());

		this.eventName=llEvent.getEventName().getvalue();
		
		if(llEvent.getInitialEventSelect()!=null) {
			for(org.mobicents.slee.container.component.deployment.jaxb.slee11.sbb.InitialEventSelect ies:llEvent.getInitialEventSelect()) {
				this.initialEventSelects.add(InitialEventSelectVariable.valueOf(ies.getVariable()));
			}
		}
		
		if(llEvent.getInitialEventSelectorMethodName()!=null )
		{
			this.initialEventSelectorMethod=llEvent.getInitialEventSelectorMethodName().getvalue();
		}
		if(llEvent.getEventResourceOption()!=null )
		{
			this.resourceOption=llEvent.getEventResourceOption().getvalue();
		}
		
		
	}

	public MEventEntry(Event event) {
		super();

		this.eventDirection = EventDirection.fromString(event
				.getEventDirection());
		this.initialEvent = Boolean.parseBoolean(event.getInitialEvent());
		this.maskOnAttach = Boolean.parseBoolean(event.getMaskOnAttach());

		// 1.1 last in transaction
		this.description = event.getDescription() == null ? null
				: event.getDescription().getvalue();

		this.eventReference=new EventTypeID(event.getEventTypeRef().getEventTypeName().getvalue(), event.getEventTypeRef().getEventTypeVendor().getvalue(), event.getEventTypeRef().getEventTypeVersion().getvalue());

		eventName=event.getEventName().getvalue();
		
		if(event.getInitialEventSelect()!=null) {
			for(InitialEventSelect ies:event.getInitialEventSelect()) {
				this.initialEventSelects.add(InitialEventSelectVariable.valueOf(ies.getVariable()));
			}
		}
		
		if(event.getInitialEventSelectorMethodName()!=null )
		{
			this.initialEventSelectorMethod=event.getInitialEventSelectorMethodName().getvalue();
		}
		if(event.getEventResourceOption()!=null )
		{
			this.resourceOption=event.getEventResourceOption().getvalue();
		}
		
		
		
	}

	public boolean isInitialEvent() {
		return initialEvent;
	}

	public EventDirection getEventDirection() {
		return eventDirection;
	}

	public boolean isMaskOnAttach() {
		return maskOnAttach;
	}

	public boolean isLastInTransaction() {
		return lastInTransaction;
	}

	public String getDescription() {
		return description;
	}

	public EventTypeID getEventReference() {
		return eventReference;
	}

	public String getEventName() {
		return eventName;
	}

	public EnumSet<InitialEventSelectVariable> getInitialEventSelects() {
		return initialEventSelects;
	}

	public String getInitialEventSelectorMethod() {
		return initialEventSelectorMethod;
	}

	public String getResourceOption() {
		return resourceOption;
	}

	/**
	 * 
	 * @return true if the event direction is Receive or FireAndReceive
	 */
	public boolean isReceived() {
		return eventDirection == EventDirection.Receive || eventDirection == EventDirection.FireAndReceive;
	}

	/**
	 * 
	 * @return true if the event direction is Fire or FireAndReceive
	 */
	public boolean isFired() {
		return eventDirection == EventDirection.Fire || eventDirection == EventDirection.FireAndReceive;
	}
}