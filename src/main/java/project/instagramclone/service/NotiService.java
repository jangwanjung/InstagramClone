package project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instagramclone.domain.noti.Noti;
import project.instagramclone.domain.noti.NotiRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotiService {
    private final NotiRepository notiRepository;

    @Transactional(readOnly = true)
    public List<Noti> 알림리스트(int loginUserId){
        return notiRepository.findByToUserId(loginUserId);
    }
}
