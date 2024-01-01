/***
 * TestDataProvider works as intermediate media to access external test data source.
 */

package testdataprovider;

import applicationsettings.ApplicationSettings;
import helper.DataReader;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    /***
     * Implementing get credentials functionality
     *
     * @return
     */
    @DataProvider(name = "Credentials")
    public static Object[][] getEnterpriseAdminCredentials() {
        return DataReader.getDataFromDataSource(ApplicationSettings.getProductionTestDataFilePath(),
                ApplicationSettings.getLoginCredentialsSheetName(),
                ApplicationSettings.getEnterpriseAdminLoginCredentialsTableName());
    }

    @DataProvider(name = "test data")
    public static Object[][] createData() {
        return DataReader.getDataFromDataSource(ApplicationSettings.getProductionTestDataFilePath(),
                ApplicationSettings.getLoginCredentialsSheetName(),
                ApplicationSettings.getProductTableName());
    }
}
