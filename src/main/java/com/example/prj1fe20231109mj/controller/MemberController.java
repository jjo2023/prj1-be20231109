package com.example.prj1fe20231109mj.controller;

import com.example.prj1fe20231109mj.domain.Member;
import com.example.prj1fe20231109mj.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService service;

    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            if (service.add(member)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "check", params = "id")
    public ResponseEntity checkId(String id) {
        if (service.getId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(value = "check", params = "email")
    public ResponseEntity checkEmail(String email) {
        if (service.getEmail(email) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(value = "check", params = "nickName")
    public ResponseEntity checkNickName(String nickName) {
        if (service.getNickName(nickName) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("list")
    public List<Member> list() {
        return service.list();
    }



    // 회원 정보 보기
    @GetMapping
    public ResponseEntity<Member> view(String id,
                                       @SessionAttribute(value = "login", required = false) Member login) {
        // TODO : 로그인 했는 지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        if (login == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!service.hasAccess(id, login)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Member member = service.getMember(id);

        return ResponseEntity.ok(member);
    }


    // 탈퇴하려고 할 때
    @DeleteMapping
    public ResponseEntity delete(String id,
                                 HttpSession session,
                                 @SessionAttribute(value = "login", required = false) Member login) {
        // TODO : 로그인 했는 지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
        }

        if (!service.hasAccess(id, login)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403
        }

        if (service.deleteMember(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.internalServerError().build();

    }

    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member,
                               @SessionAttribute(value = "login",required = false) Member login) {
        // TODO: 로그인 했는지? 자기정보인지?

        if (login == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
        }

        if (!service.hasAccess(member.getId(), login)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403
        }

        if (service.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Member member, WebRequest request){

        if (service.login(member, request)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("logout")
    public void logout(HttpSession session){
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("login")
    public Member login(@SessionAttribute(value = "login", required = false) Member login){
        return login;
    }





}





























