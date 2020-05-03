package edu.gatech.courseManagement.repository.fileio;

import edu.gatech.courseManagement.repository.CourseManagementRepository;
import edu.gatech.courseManagement.repository.CourseManagementStore;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;
import edu.gatech.courseManagement.util.CourseManagementUtil;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * class that deals with I/O for CSV based store
 */
public class CSVCourseManagementStore implements CourseManagementStore {

    private final String FILENAME_PROP = "coursemanagement.repository.store.%s.filename";
    private final String FILE_BASEDIR = "coursemanagement.repository.store.basedir";
    // Delimiter used in CSV file
    private final String DELIMITER = ",";

    @Override
    public CourseManagementRepository getRepository() {
        String repoClazz = CourseManagementUtil.getProperty("coursemanagement.repository");
        CourseManagementRepository repo =
                (CourseManagementRepository) CourseManagementUtil.createRuntimeObject( repoClazz );
        readFiles(repo);
        return repo;
    }

    private void readFiles(CourseManagementRepository repo)
    {
        String filesToRead[] = {"courses","instructors","listings","prereqs","programs","records","requests","students"};
        String fileName, fileBaseDir;
        for(String currentObj : filesToRead)
        {
            fileName = CourseManagementUtil.getProperty( String.format( FILENAME_PROP, currentObj ) );
            fileBaseDir = CourseManagementUtil.getProperty( FILE_BASEDIR );

            readFile(  fileBaseDir + fileName,
                        EntityCreationFactory.create( currentObj )  ,
                        repo );
        }
        repo.sync();
    }

    private void readFile(String inputFileName,
                          EntityCreationStrategy entityCreationStrategy,
                          CourseManagementRepository repo)
    {
        // Input file which needs to be parsed
        BufferedReader fileReader = null;
        try {
            String line = "";
            // Create the file reader
            fileReader = new BufferedReader(new java.io.FileReader(inputFileName));

            // Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                repo.add( entityCreationStrategy.create(tokens) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
