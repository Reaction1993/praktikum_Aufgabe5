package residents.repository;

import residents.domain.Resident;
import residents.service.BaseResidentService;
import residents.service.ResidentServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import java.util.Arrays;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class MockTest {

    private BaseResidentService residentService;
    private ResidentRepository residentRepositoryMock;
    private List<Resident> residentList = new LinkedList<>();

    public MockTest() throws ParseException {
        String[][] data = {
                {"Sascha", "Schettler", "Am Großhausberg", "Furtwangen", "1993-01-01"},
                {"Sammy", "Schiel", "Hauptstraße","Triberg", "1999-04-18"},
                {"Ken", "Block", "Driftweg", "Rastatt", "1970-08-18"},
                {"Peter", "Maier", "Am Berg", "Gernsbach", "1978-03-13"},
                {"Sabine", "Maier", "Am Berg", "Gernsbach", "1980-07-09"}
        };


        for (String[] s : data)
            residentList.add(new Resident(s[0], s[1], s[2], s[3], new SimpleDateFormat("yyyy-MM-dd").parse(s[4])));

        ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(residentList);
        this.residentService = new BaseResidentService();
        this.residentService.setResidentRepository(residentRepositoryStub);

        this.residentRepositoryMock = createMock(ResidentRepository.class);
        expect(residentRepositoryMock.getResidents()).andReturn(residentList);
        replay(residentRepositoryMock);
        residentService.setResidentRepository(residentRepositoryMock);
    }
    @Test
    public void unique() throws Exception {
        Resident resident = new Resident("Sascha", "Schettler", "Am Großhausberg", "", new SimpleDateFormat("yyyy-MM-dd").parse("1993-01-01"));
        assertThat(resident.getFamilyName(), equalTo(residentService.getUniqueResident(resident).getFamilyName()));
        verify(residentRepositoryMock);
    }


    @Test
    public void filteredListTest() {
        assertThat(Arrays.asList(residentList.get(3), residentList.get(4)), equalTo(residentService.getFilteredResidentsList(new Resident("", "Mai*", "", "", null))));
        verify(residentRepositoryMock);

    }

    @Test(expected = ResidentServiceException.class)
    public void uniqueWithWildcard() throws Exception {
        residentService.getUniqueResident(new Resident("Cl*", "", "", "", null));
        verify(residentRepositoryMock);
    }
}