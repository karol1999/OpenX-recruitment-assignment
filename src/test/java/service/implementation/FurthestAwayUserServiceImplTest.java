package service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FurthestAwayUserServiceImplTest {

    @InjectMocks
    FurthestAwayUserServiceImpl furthestAwayUserService;

    @Test
    void printUsersFurthestAway() {
    }

    @Test
    void getDistance() {
        // given
        double lat1 = -37.3159;
        double lon1 = 81.1496;
        double lat2 = 40.3467;
        double lon2 = -40.1310;

        // when
        double distance = furthestAwayUserService.getDistance(lat1, lon1, lat2, lon2);

        // then
        assertEquals(distance, 15012.062631973931);
    }

}