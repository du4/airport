package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DAO;

import java.util.List;

public class DictionaryService {
    public static volatile DictionaryService instance;

    private DictionaryService(){
    }

    public static DictionaryService getInstance() {
        DictionaryService localInstance = instance;
        if (localInstance == null) {
            synchronized (DictionaryService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DictionaryService();
                }
            }
        }
        return localInstance;
    }

    public List<Role> getRoles() {
        return DAO.getDAO().roleDAO.getAll("");
    }

    public List<Pilot> getPilots(){
        return DAO.getDAO().pilotDAO.getAll("");
    }

    public List<Airhostess> getAirhostesses(){
        return DAO.getDAO().airhostessDAO.getAll("");
    }

    public List<Airport> getAirports(){
        return DAO.getDAO().airportsDAO.getAll("");
    }

    public List<Plane> getPlanes(){
        return DAO.getDAO().planeDAO.getAll("");
    }

    public List<Crew> getCrews(){
        return DAO.getDAO().crewDAO.getAll("");
    }

    public List<Command> getCommands(){
        return DAO.getDAO().commandDAO.getAll("");
    }

    public List<Permission> getPermissions(){
        return DAO.getDAO().permissionDAO.getAll("");
    }




}
