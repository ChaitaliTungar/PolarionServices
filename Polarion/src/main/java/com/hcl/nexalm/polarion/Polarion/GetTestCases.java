package com.hcl.nexalm.polarion.Polarion;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.polarion.alm.ws.client.WebServiceFactory;
import com.polarion.alm.ws.client.session.SessionWebService;
import com.polarion.alm.ws.client.testmanagement.TestManagementWebService;
import com.polarion.alm.ws.client.tracker.TrackerWebService;
import com.polarion.alm.ws.client.types.tracker.LinkedWorkItem;
import com.polarion.alm.ws.client.types.tracker.WorkItem;

public class GetTestCases {

	static SessionWebService sessionService;
	static TestManagementWebService testService;
	static TrackerWebService trackerService;

	public GetTestCases(String serverURL) throws MalformedURLException, ServiceException {
		WebServiceFactory factory = new WebServiceFactory(serverURL + "/ws/services/");
		sessionService = factory.getSessionService();
		testService = factory.getTestManagementService();
		trackerService = factory.getTrackerService();
	}
	
	public static void main(String[] args) throws MalformedURLException, ServiceException, RemoteException {
		  String serverURL = "https://almdemo.polarion.com/polarion";
		  String username = "chaitalidhana.tunga@hcl.com";
		  String password = "k9Zr*C?Hd";
		 String projectId = "TrainingSampleProject";
		  String workItemId = "TSP-515";
		 String type = "testcase";
		  GetTestCases s = new GetTestCases(serverURL);
		
		 s.getListOfTestCases(username, password, projectId, workItemId, type);
		}

	public void getListOfTestCases(String username, String password, String projectId, String workItemId, String type) throws RemoteException {
		 sessionService.logIn(username, password);
		 
		 WorkItem work = trackerService.getWorkItemById(projectId, workItemId);
		 
		 
		 LinkedWorkItem[] linked = work.getLinkedWorkItems();
		 System.out.println("Length is : "+linked.length);
		
		 for(LinkedWorkItem w : linked) {
			 
			 WorkItem workItem = trackerService.getWorkItemByUri(w.getWorkItemURI());
			 
			 System.out.println("WorkItem Id : "+workItem.getId());
			 
		 
		 }
	}
}
