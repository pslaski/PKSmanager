package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.model.Filter;

import com.example.jeedemo.domain.Bus;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.domain.History;
import com.example.jeedemo.domain.Route;
import com.example.jeedemo.service.BusManager;
import com.example.jeedemo.service.DriverManager;
import com.example.jeedemo.service.HistoryManager;
import com.example.jeedemo.service.RouteManager;

@SessionScoped
@Named("historyBean")
public class HistoryFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private History history = new History();
	private Date date = new Date();
	private ListDataModel<History> histories = new ListDataModel<History>();
	private List<History> historiesForDate = new ArrayList<History>();
	private Long driverId = 0L;
	private Long busId = 0L;
	private Long routeId = 0L;
	private Long driverFilter;
	private Long routeFilter;
	private Long busFilter;
	private Date dateFilter;
	
	@Inject
	HistoryManager hm;
	@Inject
	DriverManager dm;
	@Inject
	BusManager bm;
	@Inject
	RouteManager rm;
	
	
	public Date getDateFilter() {
		return dateFilter;
	}

	public void setDateFilter(Date dateFilter) {
		this.dateFilter = dateFilter;
	}

	public Long getDriverFilter() {
		return driverFilter;
	}

	public void setDriverFilter(Long driverFilter) {
		this.driverFilter = driverFilter;
	}

	public Long getRouteFilter() {
		return routeFilter;
	}

	public void setRouteFilter(Long routeFilter) {
		this.routeFilter = routeFilter;
	}

	public Long getBusFilter() {
		return busFilter;
	}

	public void setBusFilter(Long busFilter) {
		this.busFilter = busFilter;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		historiesForDate = hm.getHistoryByDate(date);
		this.date = date;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public ListDataModel<History> getAllHistories() {
		histories.setWrappedData(hm.getAllHistory());
		return histories;
	}
	
	//ACTIONS
	
	public String addHistory() {
		history.setDriver(dm.getDriver(driverId));
		history.setBus(bm.getBus(busId));
		history.setRoute(rm.getRoute(routeId));
		history.setDate(date);
		hm.addHistory(history);
		history = new History();
		driverId = 0L;
		busId = 0L;
		routeId = 0L;
		historiesForDate = hm.getHistoryByDate(date);
		return "showLines?faces-redirect=true";
	}

	public String deleteHistory() {
		History historyToDelete = histories.getRowData();
		hm.deleteHistory(historyToDelete);
		return null;
	}
	
	//HELPERS
	public SelectItem[] getDriverOptions() {
		List<Driver> drivers = dm.getFreeDrivers();
		List<Driver> freeDrivers = new ArrayList<Driver>();
		if(historiesForDate.isEmpty()){
			SelectItem[] items = new SelectItem[drivers.size()];
		    int i = 0;
		    for(Driver d: drivers) {
		      items[i++] = new SelectItem(d.getId().toString(), d.toString());
		    }
		    return items;
		}
		else {
			boolean isFree = true;
			for(Driver d: drivers){
				for(History his : historiesForDate){
					if(d.getId().equals(his.getDriver().getId()) ){
						isFree = false;
						break;
					}						
				}
				if(isFree){
					freeDrivers.add(d);
				}
				else isFree = true;
			}
		    SelectItem[] items = new SelectItem[freeDrivers.size()];
		    int i = 0;
		    for(Driver d: freeDrivers) {
		      items[i++] = new SelectItem(d.getId().toString(), d.toString());
		    }
		    return items;
		}
		
	  }
	
	public SelectItem[] getBusOptions() {
		List<Bus> buses = bm.getWorkingBus();
		List<Bus> freeBuses = new ArrayList<Bus>();
		if(historiesForDate.isEmpty()){
			SelectItem[] items = new SelectItem[buses.size()];
		    int i = 0;
		    for(Bus b: buses) {
		      items[i++] = new SelectItem(b.getId().toString(), b.toString());
		    }
		    return items;
		}
		else {
			boolean isFree = true;
			for(Bus b: buses){
				for(History his : historiesForDate){
					if(b.getId().equals(his.getBus().getId())){
						isFree = false;
						break;
					}
				}
				if(isFree){
					freeBuses.add(b);
				}
				else isFree = true;
			}
		    SelectItem[] items = new SelectItem[freeBuses.size()];
		    int i = 0;
		    for(Bus b: freeBuses) {
		      items[i++] = new SelectItem(b.getId().toString(), b.toString());
		    }
		    return items;
		}
		
	  }
	
	public SelectItem[] getRouteOptions() {
		List<Route> routes = rm.getAllRoutes();
		List<Route> freeRoutes = new ArrayList<Route>();
		if(historiesForDate.isEmpty()){
			SelectItem[] items = new SelectItem[routes.size()];
		    int i = 0;
		    for(Route r: routes) {
		      items[i++] = new SelectItem(r.getId().toString(), r.toString());
		    }
		    return items;
		}
		else {
			boolean isFree = true;
			for(Route r: routes){
				for(History his : historiesForDate){
					if(r.getId().equals(his.getRoute().getId())){
						isFree = false;
						break;
					}
				}
				if(isFree){
					freeRoutes.add(r);
				}
				else isFree = true;
			}
		    SelectItem[] items = new SelectItem[freeRoutes.size()];
		    int i = 0;
		    for(Route r: freeRoutes) {
		      items[i++] = new SelectItem(r.getId().toString(), r.toString());
		    }
		    return items;
		}
		
	  }
	
	public SelectItem[] getRouteAllOptions() {
		List<Route> routes = rm.getAllRoutes();
	    SelectItem[] items = new SelectItem[routes.size()+1];
	    items[0] = new SelectItem("");
	    int i = 1;
	    for(Route r: routes) {
	      items[i++] = new SelectItem(r.getId().toString(), r.toString());
	    }
	    return items;
	  }
	
	public SelectItem[] getDriverAllOptions() {
		List<Driver> drivers = dm.getAllDrivers();
	    SelectItem[] items = new SelectItem[drivers.size()+1];
	    items[0] = new SelectItem("");
	    int i = 1;
	    for(Driver d: drivers) {
	      items[i++] = new SelectItem(d.getId().toString(), d.toString());
	    }
	    return items;
	  }
	
	public SelectItem[] getBusAllOptions() {
		List<Bus> buses = bm.getAllBus();
	    SelectItem[] items = new SelectItem[buses.size()+1];
	    items[0] = new SelectItem("");
	    int i = 1;
	    for(Bus b: buses) {
	      items[i++] = new SelectItem(b.getId().toString(), b.toString());
	    }
	    return items;
	  }
	
	//FILTERS
	
    public Filter<?> getFilterRoute() {
        return new Filter<History>() {
            public boolean accept(History t) {
                Long routeId = getRouteFilter();
                if (routeId == null ||  routeId.equals(t.getRoute().getId())) {
                    return true;
                }
                return false;
            }
        };
    }
    
    public Filter<?> getFilterDriver() {
        return new Filter<History>() {
            public boolean accept(History t) {
                Long driverId = getDriverFilter();
                if (driverId == null ||  driverId.equals(t.getDriver().getId())) {
                    return true;
                }
                return false;
            }
        };
    }
    
    public Filter<?> getFilterBus() {
        return new Filter<History>() {
            public boolean accept(History t) {
                Long busId = getBusFilter();
                if (busId == null ||  busId.equals(t.getBus().getId())) {
                    return true;
                }
                return false;
            }
        };
    }
    
    public Filter<?> getFilterDate() {
        return new Filter<History>() {
            public boolean accept(History t) {
                Date date = getDateFilter();
                if (date == null ||  date.equals(t.getDate())) {
                    return true;
                }
                return false;
            }
        };
    }

}
