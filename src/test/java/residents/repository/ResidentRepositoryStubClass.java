package residents.repository;

import static org.junit.Assert.*;
import residents.domain.Resident;
import residents.repository.ResidentRepository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ResidentRepositoryStubClass implements ResidentRepository {
    public List<Resident> getResidents() {
        Resident residentOne = new Resident("Sascha", "Sche", "Am-A-Bau", "Furtwangen", new Date(1993,06,23));
        Resident residentTwo = new Resident("Peter", "Maier", "Max-Mustermann-Weg", "Triberg", new Date(1991, 11, 28));
        List<Resident> residentList = new LinkedList<Resident>();
        residentList.add(residentOne);
        residentList.add(residentTwo);

        return residentList;
    }
}