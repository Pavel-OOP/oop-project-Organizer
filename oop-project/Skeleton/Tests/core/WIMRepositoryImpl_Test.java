package core;

import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WIMRepositoryImpl_Test {

    public WIMRepositoryImpl wimRepository;

    @BeforeEach
    public void beforeEach(){
        wimRepository = new WIMRepositoryImpl();
    }

    @Test
    public void checkIf_WIMRepository_adds_TeamImpl_WithValidArguments(){
        TeamImpl teamTest = new TeamImpl("TestTeam");

        wimRepository.addTeam("TestTeam", teamTest);
    }
}
