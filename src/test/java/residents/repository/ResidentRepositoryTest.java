package residents.repository;

import static org.junit.Assert.*;
import org.junit.Test;
import org.hamcrest.CoreMatchers;
import residents.service.ResidentServiceException;
import residents.repository.ResidentRepository;
import residents.service.BaseResidentService;
import residents.domain.Resident;

import java.security.UnrecoverableEntryException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.text.SimpleDateFormat;

public class ResidentRepositoryTest {
    private BaseResidentService residentService;

    private String[][] data = {
            {"Sascha", "Schettler", "Am Großhausberg", "Furtwangen", "1993-01-01"},
            {"Sammy", "Schiel", "Hauptstraße","Triberg", "1999-04-18"},
            {"Ken", "Block", "Driftweg", "Rastatt", "1970-08-18"},
            {"Peter", "Maier", "Am Berg", "Gernsbach", "1978-03-13"},
            {"Sabine", "Maier", "Am Berg", "Gernsbach", "1980-07-09"}
    };

    public ResidentRepositoryTest() throws ParseException {
        List<Resident> list = new LinkedList<>();

        for (String[] s : this.data)
            list.add(new Resident(s[0], s[1], s[2], s[3], new SimpleDateFormat("yyyy-MM-dd").parse(s[4])));

        ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(list);
        this.residentService = new BaseResidentService();
        this.residentService.setResidentRepository(residentRepositoryStub);
    }

    @Test
    public void filterListTestwithWildcard() {


        List<Resident> filteredList = this.residentService.getFilteredResidentsList(new Resident("S*", "", "", "", null));
        assertEquals("Sascha", filteredList.get(0).getGivenName());
        assertEquals("Sammy", filteredList.get(1).getGivenName());

        filteredList = this.residentService.getFilteredResidentsList(new Resident("", "Sch*", "", "", null));
        assertEquals("Schettler", filteredList.get(0).getFamilyName());
        assertEquals("Schiel", filteredList.get(1).getFamilyName());

        filteredList = this.residentService.getFilteredResidentsList(new Resident("Sammy", "", "", "Triberg", null));
        assertEquals("Schiel", filteredList.get(0).getFamilyName());


    }
    @Test
    public void uniqueTest() throws ResidentServiceException{
        assertEquals(this.residentService.getUniqueResident(new Resident("Sabine", "", "", "", null)).getFamilyName(),  "Maier");
        assertEquals(this.residentService.getUniqueResident(new Resident("", "Block", "", "", null)).getGivenName(),  "Ken");
        assertEquals(this.residentService.getUniqueResident(new Resident("", "", "Driftweg", "", null)).getFamilyName(),  "Block");
    }

    @Test(expected = ResidentServiceException.class)
    public void notUnique() throws ResidentServiceException{
        this.residentService.getUniqueResident(new Resident("", "Maier", "", "", null));
    }
}