package com.han.natty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UFile {

    public static void main(String[] args) throws IOException {
        String pathname = "/Users/lea/Desktop/uu.txt";
        File file = new File(pathname);

        if(!file.exists()){
            file.createNewFile();
        }

//        Path files2Read = Paths.get("/Users/lea/Desktop/drdss/src/cn/slt/service");
//        Path files2Read = Paths.get("/Users/lea/Desktop/drdss/src/cn/slt/service/impl");
        //Path files2Read = Paths.get("/Users/lea/Desktop/drdss/src/cn/slt/domian");
//        Path files2Read = Paths.get("/Users/lea/IdeaProjects/company/acfw/acfw-web/src/main/java/com/sztech/acfw/web/controller");
//        Path files2Read = Paths.get("/Users/lea/IdeaProjects/company/acfw/acfw-lib/src/main/java/com/sztech/acfw/lib/service");
//        Path files2Read = Paths.get("/Users/lea/IdeaProjects/company/acfw/acfw-lib/src/main/java/com/sztech/acfw/lib/service/impl");
        Path files2Read = Paths.get("/Users/lea/IdeaProjects/company/acfw/acfw-lib/src/main/java/com/sztech/acfw/lib/entity");
//        Path files2Read = Paths.get("/Users/lea/Desktop/drdss/src/cn/slt/controller");
        List<Path> collect = Files.list(files2Read)
                .filter(it -> it.toFile().isFile())
                .collect(Collectors.toList());


        Writer writer = new BufferedWriter(new FileWriter(file, true));
        for(Path path : collect){

            List<String> strings = Files.readAllLines(path);

            for(String line : strings){
                writer.append(line);
                writer.append("\n");
            }

        }

        writer.close();

    }
}
