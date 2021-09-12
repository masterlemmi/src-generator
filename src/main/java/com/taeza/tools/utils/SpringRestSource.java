package com.taeza.tools.utils;

import lombok.NonNull;

import java.io.*;
import java.util.*;

public class SpringRestSource {
    private final List<String> fileList;
    private final String outputDir;
    private final String entityName;
    private final String packageName;
    private static final String RESOURCE_DIR = "spring-rest";

    /**
     * @param entityObjectName -- the name of the Entity (will be appended with DTO/Service/Rpostiroy etc
     * @param packageName      -- all created java files will be placed in one package. last package will serve as the folder name
     */
    public SpringRestSource(@NonNull String entityObjectName, @NonNull String packageName) throws IOException {
        if (!SrcUtil.isValidJavaPackage(packageName)) {
            throw new IllegalArgumentException("Nnot a valid packageName");
        }

        this.entityName = entityObjectName;
        this.packageName = packageName;
        this.outputDir = packageName.substring(packageName.lastIndexOf(".") + 1);

        this.fileList = getResourceFiles(RESOURCE_DIR);
    }

    public void generate() throws IOException {


        Map<String, List<String>> contentByFileName = new HashMap<>();
        for (String file : fileList) {
            List<String> content = getResourceFiles(RESOURCE_DIR + "/" + file);
            contentByFileName.put(file, content);
        }

        //write files
        File dir = new File("./" + outputDir);

        if (dir.exists() ) {
           Arrays.stream(dir.listFiles()).forEach(File::delete);
           dir.delete();
        }
        boolean created = dir.mkdir();
        if (!created) {
            throw new IllegalStateException("output dir was not created: " + outputDir);
        }
        FileWriter myWriter;
        for (String key : contentByFileName.keySet()) {
            String newFileName = key.replaceAll("MockObject", entityName);
            String dirAndFile = "./" + outputDir + "/" + newFileName;
            myWriter = new FileWriter(dirAndFile);
            for (String line : contentByFileName.get(key)) {
                String updatedLine = line
                        .replaceAll("com.taeza.tools.common.util.srcmaker", packageName)
                        .replaceAll("MockObject", entityName);
                myWriter.write(updatedLine);
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Created " + dirAndFile);
        }

    }


    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) throws IOException {
        SpringRestSource mkr = new SpringRestSource("Test", "com.taeza.tools.common.util.delme");
        mkr.generate();
    }


}
