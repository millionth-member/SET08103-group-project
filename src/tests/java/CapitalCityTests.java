import com.napier.sem.group10.App;
import com.napier.sem.group10.ICommandHandler;
import com.napier.sem.group10.filters.capitalcity.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CapitalCityTests {

    private static Helper _helper;

    private static CapitalCitiesInContinent CapCont;
    private static CapitalCitiesInRegion CapReg;
    private static CapitalCitiesInWorld CapWorld;

    @BeforeAll
    public static void Init() throws IOException, InterruptedException {
        App app = new App();
        _helper = new Helper(app.GetSqlConnection());

        CapCont = new CapitalCitiesInContinent();
        CapReg = new CapitalCitiesInRegion();
        CapWorld = new CapitalCitiesInWorld();
    }

    @Test
    public void CapitalCitiesInContinentCommandValid()
    {
        assertNotNull(CapCont.getCommand());
        assertTrue(CapCont.getCommand().startsWith("capitalcity"));
    }

    @Test
    public void CapitalCitiesInRegionCommandValid()
    {
        assertNotNull(CapReg.getCommand());
        assertTrue(CapReg.getCommand().startsWith("capitalcity"));
    }

    @Test
    public void CapitalCitiesInWorldCommandValid()
    {
        assertNotNull(CapWorld.getCommand());
        assertTrue(CapWorld.getCommand().startsWith("capitalcity"));
    }

    @Test
    public void CapitalCitiesInContinentQueryCorrect() {
        var args = new HashMap<String, String>();
        args.put("continent", "Europe");
        var rows = new String[]{
                "\"Moscow\",\"Russian Federation\",8389200",
                "\"London\",\"United Kingdom\",7285000",
                "\"Berlin\",\"Germany\",3386667",
                "\"Madrid\",\"Spain\",2879052",
                "\"Roma\",\"Italy\",2643581",
                "\"Kyiv\",\"Ukraine\",2624000"
        };
        _helper.AssertQuery(CapCont, args, rows);
    }

    @Test
    public void CapitalCitiesInRegionQueryCorrect() {
        var args = new HashMap<String, String>();
        args.put("region", "Eastern Asia");
        var rows = new String[]{
                "\"Seoul\",\"South Korea\",9981619",
                "\"Tokyo\",\"Japan\",7980230",
                "\"Peking\",\"China\",7472000",
                "\"Taipei\",\"Taiwan\",2641312",
                "\"Pyongyang\",\"North Korea\",2484000",

        };
        _helper.AssertQuery(CapReg, args, rows);
    }

    @Test
    public void CapitalCitiesInWorldQueryCorrect() {
        var args = new HashMap<String, String>();
        var rows = new String[]{
                "\"Seoul\",\"South Korea\",9981619",
                "\"Jakarta\",\"Indonesia\",9604900",
                "\"Ciudad de México\",\"Mexico\",8591309",
                "\"Moscow\",\"Russian Federation\",8389200",
                "\"Tokyo\",\"Japan\",7980230",
                "\"Peking\",\"China\",7472000",
                "\"London\",\"United Kingdom\",7285000",

        };
        _helper.AssertQuery(CapWorld, args, rows);
    }


}
