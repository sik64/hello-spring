package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();//???????
    //이 맵은 키가 Long 타입이고 값이 Member 타입인 요소들을 저장합니다.
    // 딕셔너리 MAP사용법 !
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // optional로 감싸면 만약에 null이어도 처리 가능하다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store는 HashMap이고 HashMap의 메소드 공부!
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
