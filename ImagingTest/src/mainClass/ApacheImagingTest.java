package mainClass;

import java.awt.image.BufferedImage;
import java.util.Scanner;
import org.apache.commons.imaging.common.ImageMetadata;

public class ApacheImagingTest {
	public static void main(String[] args) throws Exception{
        //����Ĭ�ϵ�ͼƬ�洢·��
        String filePathHead = "C:\\Users\\Administrator\\Desktop\\ѧУ�γ����ļ���\\�������\\�������ʵ��\\testpicture";
        String pictureName = "";
        Scanner scanner = new Scanner(System.in);
        //ѯ���û��Ƿ��޸ĵ�ַ
        System.out.println("��ǰ�ļ�·����:" + filePathHead);
        System.out.print("�Ƿ�����ļ�·����(��������Y����������N)");
        String choice = scanner.nextLine();
        if(choice.equals("Y") || choice.equals("y")){
            System.out.print("�������ļ�·��:");
            filePathHead = scanner.nextLine();
        }
        //����ͼƬ�Ļ�����Ϣ
        System.out.print("������ͼƬ��:");
        pictureName = scanner.nextLine();
        System.out.println("��ѡ��ͼƬ��ʽ:");
        System.out.println("1 .jpg");
        System.out.println("2 .jpeg");
        System.out.println("3 .png");
        System.out.print("���������ѡ��:");
        int formatChoice = scanner.nextInt();
        String imageFormat = "";
        imageFormat = scanner.nextLine();//��ȡ�س�����
        switch(formatChoice){
            case 1: imageFormat = ".jpg";break;
            case 2: imageFormat = ".jpeg";break;
            case 3: imageFormat = ".png";break;
            default: imageFormat = "�ļ���ʽ����";break;
        }
        PictureGet goalPicture = new PictureGet(filePathHead, pictureName, imageFormat);
        //�ж�ͼƬ�Ƿ����
        boolean existence = goalPicture.judgeExistence();
        if(!existence) {
            //�����ļ������ڣ���ʾ��Ϣ
            System.out.println("�����ļ�������");
            System.out.println("����ͼƬ��Ϣ�Ƿ���ȷ");
            System.out.println("ͼƬλ��: " + goalPicture.getFilePath());
            System.out.println("ͼƬ��ʽ: " + goalPicture.getFormat());
            System.out.println("ͼƬ��: " + goalPicture.getPictureName());
        }
        else{
            //����ʱ�������в���
            System.out.println("ͼƬ��ȡ�ɹ�");
            //��ȡͼƬ��Exif��Ϣ
            ImageMetadata image = goalPicture.getExif(goalPicture.getPicturePath());
            //��ӡͼƬ��EXIF��Ϣ
            goalPicture.printExif(image);
            //�޸�ͼƬ�������ص��RGB�����޸ĺ��ͼƬ���浽Դ�ļ���ͬ���ļ�����
            BufferedImage orgImage = PictureIO.getGoalImage(goalPicture.getPicturePath());
            if(orgImage != null) {
                System.out.println("�������޸����ص�����꣨������ص�ͬrgb�����ص���ɫ����ı�Ϊָ��ֵ��");
                System.out.print("�����������:");
                int xiangsu_x = scanner.nextInt();
                System.out.print("������������:");
                int xiangsu_y = scanner.nextInt();
                System.out.println("�����������ı�����ɫrgbֵ");
                System.out.print("red:");
                int new_red = scanner.nextInt();
                System.out.print("green:");
                int new_green = scanner.nextInt();
                System.out.print("blue:");
                int new_blue = scanner.nextInt();
                System.out.print("͸����:");
                int new_ar = scanner.nextInt();
                BufferedImage newImage = SetRGB.setRGB(orgImage, xiangsu_x,
                        xiangsu_y, new_red, new_green, new_blue, new_ar);
                //�������޸ĺ��ͼƬʱ��������Ϊ�������ص���󣬻���rgb��ɫ������Χ���᷵��NULL
                if(newImage != null) {
                    System.out.print("��������ͼƬ��:");
                    String newPictureName = scanner.nextLine();
                    newPictureName = scanner.nextLine();
                    if(!PictureIO.writeGoalImage(newImage, goalPicture.getFilePath(), newPictureName)){
                        System.out.println("д��ͼƬ����");
                    }
                }
                else {
                    System.out.println("������������ص㣬rgb��ɫֵ����͸�����Ƿ�Խ��");
                    System.out.println("��ͼƬ���ص㷶Χ����:" + orgImage.getWidth() +" ��:" + orgImage.getHeight());
                    System.out.println("rgb��ɫֵ����͸���ȷ�Χ0~255");
                    System.out.println("����������ص����꣬xֵ:" + xiangsu_x + " yֵ:" + xiangsu_y);
                    System.out.println("rgb��ɫֵ��red: " + new_red + " green: " + new_green + " blue: " + new_blue);
                    System.out.println("��͸����: " + new_ar);
                }
            }
            else {
                System.out.println("�������й�����ͼƬ���Ƴ�������");
            }
        }

    }
}
