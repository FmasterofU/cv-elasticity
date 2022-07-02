package rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos;

import org.springframework.web.multipart.MultipartFile;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

import java.util.Arrays;

public class UploadModel extends IndexUnit {

    private MultipartFile[] files;

	public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
