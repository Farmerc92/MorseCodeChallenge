import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DecoderTest {

    @Test
    @Parameters({
            "XXXOXOX, D",
            "XXXOXOXOOO, D",
            "XXXOXOXOOOOOO, D",
            "XOXOXOXXXOXXXOOO, 3",
            "XXXOXOXXXOXOOOXOXXXOOOXXXOOO, CAT"
    })
    public void basicTests(String encoded, String decoded) {
        Assert.assertEquals(decoded, Decoder.decode(encoded));
    }

    @Test(expected = IllegalArgumentException.class)
    public void basicTest4() {
        String encoded = "XOXOXOX/XXOXXXOOO";
        String decoded = "3";

        Assert.assertEquals(decoded, Decoder.decode(encoded));
    }

    @Test
    @Parameters({
            "XXOOXXOOOOOOXXXXXXOOXXOOOOOOXXXXXXOOOOOOXXOOOOOOXXOOXXXXXXOOXXOOXXOOOOOOXXOOXXXXXXOOXXOOXXOOOOOOXXOOXXOOOOOOXXOOXXXXXXOOXXXXXXOOXXXXXXOOOOOO, INTELLIJ",
            "XOXOXXXOXOOOXOXXXOOOXOXXXOXOOOXOXXXOOOXOXXXOXXXOOOXOXXXOOOXXXOXOXXXOXXXOOOOOOOXOXXXOXOXOOOXOXXXOOOXOXOXXXOOOXXXOXOOOXXXOXOXOOOXOXXXOXOOOXXXOXOXXXOXXXOOO, FARAWAY LAUNDRY",
            "XXXOOOXXXXXXXXXOOOXXXOOOOOOOOOXXXOOOXXXOOOXXXXXXXXXOOOOOOOOOXXXOOOXXXOOOXXXOOOOOOOOOXXXXXXXXXOOOOOOOOOXXXOOOXXXOOOOOOOOOXXXXXXXXXOOOXXXOOOXXXXXXXXXOOOXXXOOOOOOOOOOOOOOOOOOOOOXXXOOOXXXXXXXXXOOOXXXOOOOOOOOOXXXXXXXXXOOOXXXXXXXXXOOOXXXXXXXXXOOOOOOOOOXXXXXXXXXOOOXXXXXXXXXOOOXXXXXXXXXOOOOOOOOOXXXOOOXXXOOOXXXXXXXXXOOOXXXOOOOOOOOOXXXXXXXXXOOOOOOOOOXXXXXXXXXOOOXXXXXXXXXOOOXXXXXXXXXOOOOOOOOOXXXOOOXXXXXXXXXOOOXXXXXXXXXOOOXXXOOOOOOOOO, RUSTIC ROOFTOP",
            "XXXOOOXOXOXOXOOOXOXOOOXOXOXOOOOOOOXOXOOOXOXOXOOOOOOOXOXXXOOOOOOOXOXXXOXOXOOOXOXXXOOOXXXOXXXOOOXOOOXXXOXOOOXXXOOOXOXXXOOOXXXOXOXOXOOOXOXXXOXOXOOOXOOOOOOOXXXOOOXOXOXXXOOOXOXXXOXOOOXXXOXOOOOOOOXXXOXXXOXXXOOOXOXOXXXOXOOOOOOOXOOOXOXOXOXXXOOOXOOOXXXOXOOOXXXOOOXOXOXOOO, THIS IS A LAMENTABLE TURN OF EVENTS",
            "XXXXXXXXXXXXXXXOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXXXXXXXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXXXXXXXXXXXOOOOOXXXXXOOOOOOOOOOOOOOOXXXXXOOOOOXXXXXOOOOOXXXXXOOOOOOOOOOOOOOO, KIND OF A LOT OF LETTERS",
            "OOO0O0OOO0O000O0O0O0O000O0O000OOO0OOO000O000O0OOO0O000O0OOO000, CHIMERA",
            "TSTTTSTSSSTSTSTTTSSSTTTSTSSSTSTTTSSSTSTTTSTTTSSSTSTTTSSSTTTSTSTTTSTTTSSSSSSSTTTSTSSSTSTTTSSSTTTSSSTSTSTTTSSSTSTTTSTSSSTSSSSSSSTSTSTSTTTSSSTSTSTSSSSSSSTTTSTSSSTSTSTTTSSSTSTTTSTSSSTTTSSSTSTSTTTSSSTSTTTSTSSSTSSS, RUNAWAY NATURE VS NURTURE",
    })
    public void moderateTests(String encoded, String decoded) {
        Assert.assertEquals(decoded, Decoder.decode(encoded));
    }

    @Test
    @Parameters({
            "EEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEIIIEEEIIIIIIIIIEEEIIIEEEIIIEEEEEEEEEIIIIIIIIIEEEIIIEEEIIIEEEIIIIIIIIIEEEIIIEEEIIIEEEIIIEEEIIIIIIIIIIIIIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEIIIEEEIIIEEEIIIIIIIIIIIIIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEIIIEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIIIIIIIEEEEEEEEEIIIEEEIIIIIIIIIIIIIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEIIIEEEIIIEEEEEEEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEEEEEEEIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIIIIIIIIIIIIIIIIIIIEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEIIIIIIIIIEEEEEEEEEIIIEEEEEEEEEIIIEEEEEEEEEIIIIIIIIIEEEEEEEEEIIIEEEIIIEEEEEEEEEIIIIIIIIIEEEIIIIIIIIIEEEIIIEEEIIIEEEIIIIIIIII, PLUSH POPS PLAN PUPPY POKES",
            "RPPPRPPPRPRPRRRRRRRRRR, Z",
            "RRRRRRRRRRPPPPPPRRPPRRPPPPPPRRRRRRPPPPPPRRPPPPPPRRPPPPPPRRRRRRPPRRPPPPPPRRRRRRPPRRPPPPPPRRPPRRPPRRRRRRPPRRPPPPPPRRRRRRRRRR, KOALA",
            "OOOOOOOOOOOOOOOQQQQQQQQQOOOQQQOOOQQQQQQQQQOOOOOOOOOQQQQQQQQQOOOQQQQQQQQQOOOQQQQQQQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOQQQOOOQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOOOOOOOQQQOOOQQQOOOQQQOOOOOOOOOOOOOOOOOOOOOQQQOOOQQQQQQQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOQQQOOOOOOOOOQQQOOOOOOOOOOOOOOOOOOOOOQQQQQQQQQOOOQQQOOOOOOOOOQQQQQQQQQOOOQQQQQQQQQOOOQQQQQQQQQOOOOOOOOOQQQQQQQQQOOOOOOOOOOOOOOOOOOOOOQQQQQQQQQOOOQQQOOOQQQOOOQQQOOOOOOOOOQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOOOOOOOQQQOOOQQQQQQQQQOOOQQQOOOOOOOOOQQQOOOQQQOOOQQQOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO, KOALAS ARE NOT BEARS",
            "T111111111111111IIIIIIIIIIII111111Y11111IIII1111IIII1111IIII1111ITII111111U11111IIII1111IIII111111111111IIII1111IIII1111IIII1111111111111111111111111111IIII1111IIIIIIIIIIII1111IIIIIITIIIII111111111111IIII1111IIIIIIIIIIII111111111111IIII1111IIII1111IIII111111RR11111111111111111111IIII1111IIIIIIIIIIII1111IIIIIIIIIIII1111IIII111111111111IIII1111IIIIIIIIIIII1111IIII111111T11111IIII111111111111IIIIIIIIIIII111111111111IIIIIIIIIIII111111111111IIIIIIIIIIII1111IIII1111IIIIIIIIIIII1111IIIIIIIIIIII1111111111111111111111111111IIIIIIIIIIII1111IIII1111IIII111111111111IIII1111IIII111111111111IIII1111IIII1111IIIIIIIIIIII1111IIII111111111111IIII1111IIII1111IIIIIIIIIIII1111IIII111111111111IIII1111IIII111111111111IIIIIIIIIIII1111IIII1111IIIIIIIIIIII1111IIII111111111111IIII1111IIII1111IIIIIITIIIII111111111111IIII1111IIIIIIIIIIII1111IIII1111IIII111111111111IIIIIIIIIIII11111111111T111111111111111111111111, THIS WAS PRETTY DIFFICULT",
    })
    public void advancedTests(String encoded, String decoded) {
        Assert.assertEquals(decoded, Decoder.decode(encoded));
    }

    @Test
    public void teststuff(){
        Assert.assertEquals("FARAWAY LAUNDRY", Decoder.decode("XOXOXXXOXOOOXOXXXOOOXOXXXOXOOOXOXXXOOOXOXXXOXXXOOOXOXXXOOOXXXOXOXXXOXXXOOOOOOOXOXXXOXOXOOOXOXXXOOOXOXOXXXOOOXXXOXOOOXXXOXOXOOOXOXXXOXOOOXXXOXOXXXOXXXOOO"));
    }

}