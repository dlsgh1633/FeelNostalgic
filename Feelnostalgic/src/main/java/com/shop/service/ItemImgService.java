package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    @Value("${itemImgLocation}") //properties -> itemImgLocation=C:/shop1/item
    private String itemImgLocation;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile)throws Exception{

        String oriImgName = itemImgFile.getOriginalFilename(); // 오리지날 이미지 경로
        String imgName = "";
        String imgUrl = "";
        System.out.println(oriImgName);
        //파일 업로드 
        if(!StringUtils.isEmpty(oriImgName)){ // oriImgName 문자열로 비어있지 않으면 실행한다는 조건.
            System.out.println("******");
                                            //(C:/shop1/item ,  a. jpg  ,  픽셀데이터->이미지파일을 배열로 바꿔준다. )
            // 리턴값으로 ssavedFileName을 받으면서 imgName 에 저장 -> 이상한문자.jpg 예 ) 3259823095809235.jpg 이런식으로 .
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,itemImgFile.getBytes()); //getBytes() -> String(문자열)을 default charset으로 인코딩하여 byte 배열로 반환해준다.
            System.out.println(imgName); // 이미지이름 출력 해서 확인해보기 .
            imgUrl = "/images/item/"+imgName; // 이미지 경로 .ㄴ
        }
        System.out.println("1111");
        //상품 이미지 정보 저장
        // oriImgName : 상품 이미지 파일의 원래 이름
        // imgName : 실제 로컬에 저장된 상품 이미지 파일의 이름
        // imgUrl : 로컬에 저장된 상품 이미지 파일을 불러오는 경로
        itemImg.updateItemImg(oriImgName,imgName,imgUrl); // 비워져서 매개변수로 채워놓은 다음 업데이트 하고 저장한다 .
        System.out.println("((((");
        itemImgRepository.save(itemImg); // save .
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile)throws Exception{
        // 파일이미지가 널이 아니라면 조건문 실행
        // 널이라면 그대로 메소드 종료 .
        if(!itemImgFile.isEmpty()){ // 상품의 이미지를 수정한 경우 상품 이미지 업데이트
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);//기존 엔티티 조회
            // 기존에 등록된 상품 이미지 파일이 있는경우 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())){ //비어있지않으면 deleteFile 메소드 호출 .
                    //fileService`를 통해 이전에 저장되어 있던 상품 이미지 파일을 삭제합니다.
                fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
            }
            //업데이트할 새로운 이미지 파일을 `fileService`를 통해 업로드하고, 새로운 이미지 파일의 정보를 얻습니다.
            // 이미지파일의 url 생성
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());// 파일업로드
            String imgUrl = "/images/item/"+imgName;
            //변경된 상품 이미지 정보를 세팅
            // 상품 등록을 하는 경우에는 ItemImgRepository.save() 로직을 호출 하지만
            // 호출을 하지않았습니다.
            //savedItemImg 엔티티는 현재 영속성 상태이다.
            // 그래서 데이터를 변경하는 것만으로 변경을 감지기능이 동작
            // 트랜잭션이 끝날때 update 쿼리가 실행된다 .
            // 영속성 상태여야함 사용가능
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }

}
