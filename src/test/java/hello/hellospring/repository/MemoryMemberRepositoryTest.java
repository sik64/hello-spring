package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// 완성된 클래스에 멤버를 넣어줘 보는 작업
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach // 모든 메소드가 끝날때 마다 하는 동작
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        // 자동으로 id는 입력됨 member.getId()
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = "+ (result==member));
        Assertions.assertEquals(member,result);


    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member result1 = repository.findByName("spring1").get();
        Assertions.assertEquals(member1,result1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result2 = repository.findByName("spring2").get();
        Assertions.assertEquals(member2,result2);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring10");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring20");
        repository.save(member2);
        List<Member> result = repository.findAll();
        for (int i=0 ; i<result.size();i++){
            Member value = result.get(i);
            System.out.println(value.getName());
        }
    }
    
}
