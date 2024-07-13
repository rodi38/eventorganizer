package soft.rodi38.eventorganizer.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import soft.rodi38.eventorganizer.model.dto.request.DonationRequest;
import soft.rodi38.eventorganizer.model.entity.Donation;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    DonationMapper INSTANCE = Mappers.getMapper(DonationMapper.class);

    Donation donationRequestToDonation(DonationRequest donationRequest);


}
