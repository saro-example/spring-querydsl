package me.saro.example.qdsl.account;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.QueryHints;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class AccountRepository {
    
    @PersistenceContext EntityManager entityManager;
    
    @Transactional
    public void insert(Account account) {
        entityManager.persist(account);
    }
    
    public List<Account> findAll(Pageable pageable) {
        QAccount ac = QAccount.account1;
        return new JPAQuery<Account>(entityManager)
                .from(ac)
                    .leftJoin(ac.roles)
                .orderBy(ac.no.asc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                .setHint(QueryHints.HINT_FETCHGRAPH, entityManager.getEntityGraph("account.roles"))
                .fetch();
    }
    
    public List<Account> findLikeAccount(String account) {
        QAccount ac = QAccount.account1;
        return new JPAQuery<Account>()
                .from(ac)
                    .leftJoin(ac.roles)
                .where(ac.account.likeIgnoreCase("%"+account+"%"))
                    .orderBy(ac.no.asc())
                .setHint(QueryHints.HINT_FETCHGRAPH, entityManager.getEntityGraph("account.roles"))
                .fetch();
    }
}