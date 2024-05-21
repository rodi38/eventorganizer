package soft.rodi38.eventorganizer.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.EventDTO;
import soft.rodi38.eventorganizer.model.entity.Event;

import java.util.List;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);


    EventDTO eventToEventDto(Event event);
    List<EventDTO> eventLitToEventDtoList(List<Event> event);
    Event eventDtoToEvent(EventDTO eventDTO);


}
