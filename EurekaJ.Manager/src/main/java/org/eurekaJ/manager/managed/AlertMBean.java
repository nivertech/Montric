package org.eurekaJ.manager.managed;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.eurekaJ.manager.berkeley.treemenu.TreeMenuNode;
import org.eurekaJ.manager.perst.alert.Alert;
import org.eurekaJ.manager.service.TreeMenuService;

public class AlertMBean {
	private TreeMenuService treeMenuService;
	private String selectedPath;
	private TreeMenuNode selectedTreeMenuNode;
	private List<SelectItem> alertTypes;
	private List<SelectItem> alertOnTypes;
	private Alert alert;
	
	public AlertMBean() {
		alertTypes = new ArrayList<SelectItem>();
		alertTypes.add(new SelectItem("gt", "Greater Than"));
		alertTypes.add(new SelectItem("lt", "Less Than"));
		alertTypes.add(new SelectItem("eq", "Equals"));
		
		alertOnTypes = new ArrayList<SelectItem>();
	}
	
	public TreeMenuService getTreeMenuService() {
		return treeMenuService;
	}
	
	public void setTreeMenuService(TreeMenuService treeMenuService) {
		this.treeMenuService = treeMenuService;
	}
	
	public String getSelectedPath() {
		return selectedPath;
	}
	
	public void setSelectedPath(String selectedPath) {
		this.selectedPath = selectedPath;
		updateModelAfterPathChange();
	}
	
	public TreeMenuNode getSelectedTreeMenuNode() {
		return selectedTreeMenuNode;
	}
	
	public List<SelectItem> getAlertTypes() {
		return alertTypes;
	}
	
	public void setAlertTypes(List<SelectItem> alertTypes) {
		this.alertTypes = alertTypes;
	}
	
	public List<SelectItem> getAlertOnTypes() {
		return alertOnTypes;
	}
	
	public void setAlertOnTypes(List<SelectItem> alertOnTypes) {
		this.alertOnTypes = alertOnTypes;
	}
	
	public Alert getAlert() {
		return alert;
	}
	
	public void setAlert(Alert alert) {
		this.alert = alert;
	} 
	
	public void saveAlert(ActionEvent event) {
		treeMenuService.persistAlert(this.alert);
	}
	
	public boolean isNodeAlertable() {
		return selectedTreeMenuNode != null;
	}
	
	public void navigateToTab(ActionEvent event) {
		updateModelAfterPathChange();
	}
	
	private void updateModelAfterPathChange() {
		selectedTreeMenuNode = treeMenuService.getTreeMenu(selectedPath);
		if (selectedTreeMenuNode != null) {
			alertOnTypes = new ArrayList<SelectItem>();
			if (selectedTreeMenuNode.isHasExecTimeInformation()) {
				alertOnTypes.add(new SelectItem(Alert.ALERT_ON_AVG_EXECTIME, "Average Execution Time"));
				alertOnTypes.add(new SelectItem(Alert.ALERT_ON_TOTAL_EXECTIME, "Total Execution Time"));
			}
			
			if (selectedTreeMenuNode.isHasCallsPerIntervalInformation()) {
				alertOnTypes.add(new SelectItem(Alert.ALERT_ON_CALLS, "Calls Per Interval"));
			}
			
			if (selectedTreeMenuNode.isHasValueInformation()) {
				alertOnTypes.add(new SelectItem(Alert.ALERT_ON_VALUE, "Value"));
			}
			
			Alert a = treeMenuService.getAlert(selectedPath);
			if (a != null) {
				this.alert = a;
			} else {
				this.alert = new Alert();
				this.alert.setGuiPath(selectedPath);
			}
		}
	}
	
}