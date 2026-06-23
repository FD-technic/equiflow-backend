package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.dto.PortfolioListDTO;
import cz.ferdo.equiflow.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    @Query("""
                        select new cz.ferdo.equiflow.dto.PortfolioListDTO(
                                    p.id,
                                    p.type,
                                    p.name,
                                    o.id,
                                    o.userName,
                                    count(pos.id)
                        )
                        from portfolio p
                        join p.owner o
                        left join p.positions pos
                        where p.owner.id = :ownerId
                        group by p.id,
                                 p.type,
                                 p.name,
                                 o.id,
                                 o.userName
            """)
    List<PortfolioListDTO> findPortfolioListByOwner(@Param("ownerId") Long ownerId);

    @Query("""
                        select new cz.ferdo.equiflow.dto.PortfolioListDTO(
                                    p.id,
                                    p.type,
                                    p.name,
                                    o.id,
                                    o.userName,
                                    count(pos.id)
                        )
                        from portfolio p
                        join p.owner o
                        left join p.positions pos
                        group by p.id,
                                 p.type,
                                 p.name,
                                 o.id,
                                 o.userName
            """)
    List<PortfolioListDTO> findPortfolioList();

}
