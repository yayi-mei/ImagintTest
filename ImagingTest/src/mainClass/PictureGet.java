package mainClass;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;

import java.io.File;
import java.io.IOException;

public class PictureGet {
	private String FilePath;
    private String PictureName;
    private String format;
    private String picturePath;
    public PictureGet(String FilePath, String PictureName, String format){
        this.FilePath = FilePath;
        this.PictureName = PictureName;
        this.format = format;
        this.picturePath = "";
    }
    //�ϲ�ͼƬ·��
    public String MergePath(){
        //�ϳ�ͼƬ�ļ�����ϸ·����ʹ�õ��Ǿ���·����
        if(this.format.equals("�ļ���ʽ����")){
            this.picturePath = "�ļ���ʽ����";
        }
        else {
            this.picturePath = this.FilePath + this.PictureName + this.format;
        }
        return this.picturePath;
    }
    //�ж������ͼƬ�Ƿ����
    public boolean judgeExistence(){
        //�Ⱥϳ�ͼƬ�ļ��ľ���·��
        String goalImagePath = this.MergePath();
        boolean result = false;
        //����ָ��Ŀ���ļ���ָ��
        File imageFile = new File(goalImagePath);
        //�ж�Ŀ���ļ��Ƿ����
        result = Imaging.hasImageFileExtension(imageFile);
        return result;
    }
    //��ȡͼƬ��EXIF��Ϣ
    public ImageMetadata getExif(String goalPath)throws ImageReadException, IOException {
        File imgFileName = new File(goalPath);
        ImageInfo sampleInfo = Imaging.getImageInfo(imgFileName);
        System.out.println("ͼƬ��ý��: " + sampleInfo.getMimeType());//��ȡͼƬ��ý������
        System.out.println("ͼƬ�ĳߴ�: " + Imaging.getImageSize(imgFileName));
        final ImageMetadata metadata = Imaging.getMetadata(imgFileName);
        return metadata;

    }
    public void printExif(final ImageMetadata metadata){
        //�ж�Ŀ���Ƿ���EXIF��Ϣ
        if(metadata instanceof JpegImageMetadata){
            //��ӡ������EXIF��Ϣ
            System.out.println("����ΪͼƬ��EXIF��Ϣ");
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_XRESOLUTION);
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_DATE_TIME);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_ISO);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            printTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
            printTagValue(jpegMetadata,
                    GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            printTagValue(jpegMetadata,
                    GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
            System.out.println();
        }
        else {
            System.out.println("Ŀ��û��EXIF��Ϣ");
        }
    }
    //��ӡ������Ϣ
    private static void printTagValue(final JpegImageMetadata jpegMetadata, final TagInfo tagInfo){
        final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
        if(field == null){
            System.out.println(tagInfo.name + ": " + "Not found");
        }
        else {
            System.out.println(tagInfo.name + ": " + field.getValueDescription());
        }
    }
    public String getPicturePath(){
        return this.picturePath;
    }
    public String getFilePath() {
        return this.FilePath;
    }
    public String getPictureName() {
        return this.PictureName;
    }
    public String getFormat() {
        return this.format;
    }
    public boolean setFilePath(String NewPath) {
        boolean result = false;
        this.FilePath = NewPath;
        result = true;
        return result;
    }
    public boolean setPictureName(String NewName) {
        boolean result = false;
        this.PictureName = NewName;
        result = true;
        return result;
    }
    public boolean setFormat(String NewFormat) {
        boolean result = false;
        this.format = NewFormat;
        result = true;
        return result;
    }
}
