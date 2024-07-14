package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.TicketResponse;
import soft.rodi38.eventorganizer.model.entity.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketResponse ticketToTicketResponse(Ticket ticket);
}
