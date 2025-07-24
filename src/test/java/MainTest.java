import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {

    @Test
    void getSum() {
        Cal cal = mock(Cal.class);
        when(cal.GetSum(1,2)).thenReturn(999);
        System.out.println(cal.GetSum(1,2));
    }
}