package residents.repository;
import residents.domain.Resident;
import java.util.List;

public class ResidentRepositoryStub implements ResidentRepository {

    private List<Resident> residentList;

    public ResidentRepositoryStub(List<Resident> residents) {
        this.residentList = residents;
    }

    @Override
    public List<Resident> getResidents() {
        return residentList;
    }
}