package hello.spring;

import hello.spring.domain.*;
import hello.spring.repository.PostRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {
    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setId(74l);
//
//        post.setTitle("테스트 게시글입니다.73");
//
//        Comment comment = new Comment();
//        comment.setComment("74테스트 커멘트 입니다.");
//
//        Comment comment2 = new Comment();
//        comment2.setComment("74테스트 커멘트 입니다.11111");
//
//        Comment comment3 = new Comment();
//        comment3.setComment("74테스트 커멘트 입니다.22222");
//
//        post.addComments(comment);
//        post.addComments(comment2);
//        post.addComments(comment3);
//
//        em.persist(post);

        //Post post1 = em.find(Post.class, 73l);
        //System.out.println(post1);

        //post1.setTitle("수정 테스트 게시글입니다.73");
        //em.clear();
        //TypedQuery<Post> query = em.createQuery("select p from Post p", Post.class);
//        Query nativeQuery = em.createNativeQuery("select * from post", Post.class);
//        List<Post> postList = nativeQuery.getResultList();
//        postList.forEach(System.out::println);

        //Session session = em.unwrap(Session.class);
        //session.save(post);
        //Post post2 = session.get(Post.class,33l);
////        session.delete(post);
//
////        Comment comment = session.get(Comment.class,34l);
////        System.out.println(comment);
//        System.out.println("=================================");
//        System.out.println(post.getTitle());
//
//        post.getComments().forEach(c -> {
//            System.out.println("------------------------------");
//            System.out.println(c.getComment());
//        });

//        TypedQuery<Post> query = em.createQuery("select p from Post p", Post.class);
//        List<Post> postList = query.getResultList();
//        postList.forEach(System.out::println);
        postRepository.findAll().forEach(System.out::println);

//        Board board = new Board();
//        board.setTitle("게시판 타이틀 1");
//
//        Board board2 = new Board();
//        board2.setTitle("게시판 타이틀 2");
//
//        Board board3 = new Board();
//        board3.setTitle("게시판 타이틀 3");
//
//        em.persist(board);
//        em.persist(board2);
//

//        Team team = new Team();
//        team.setName("lalaland2");
//        em.persist(team);
//
//        Member member = new Member();
//        member.setName("jackson2");
//        member.setAge(47);
//
//        Member member1 = new Member();
//        member1.setName("anjella2");
//        member1.setAge(40);
//
//        member.setTeam(team);
//        member1.setTeam(team);
//
//        em.persist(member);
//        em.persist(member1);
//
//        //TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//       // query.getResultList().forEach(System.out::println);
//
//        Member member2 = em.find(Member.class, 2l);
//        System.out.println(member2.getTeam().getName());

        //queryLoginJoin();
        // update();
        //deleteRelation();
       // biDirection();

    }

    private void queryLoginJoin(){
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        TypedQuery<Member> query = em.createQuery(jpql, Member.class).setParameter("teamName","jugong9complex");
        List<Member> resultList = query.getResultList();

        resultList.forEach(r -> {
            System.out.println(r.getName());
        });
    }

    public void update(){
        Team team = new Team("팀2");
        em.persist(team);

        Member member = em.find(Member.class,1l);
        member.setTeam(team);
    }

    public void deleteRelation(){
        Member member = em.find(Member.class,2l);
        member.setTeam(null);
    }

    public void biDirection(){
        Team team = em.find(Team.class,6l);
        List<Member> members = team.getMembers();
        members.forEach(System.out::println);
    }
}
