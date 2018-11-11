package constant;

public class File {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_PATH = PROJECT_PATH.concat("/src/test/resources");
    public static final String PROPERTIES_FILE_PATH = RESOURCES_PATH.concat("/config.properties");
}
