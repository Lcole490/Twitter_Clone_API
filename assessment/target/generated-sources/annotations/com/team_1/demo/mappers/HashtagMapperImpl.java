package com.team_1.demo.mappers;

import com.team_1.demo.dtos.HashtagDto;
import com.team_1.demo.entities.Hashtag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-01T15:19:08-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class HashtagMapperImpl implements HashtagMapper {

    @Override
    public Hashtag hashtagDtoToEntity(HashtagDto hashtagDto) {
        if ( hashtagDto == null ) {
            return null;
        }

        Hashtag hashtag = new Hashtag();

        hashtag.setLabel( hashtagDto.getLabel() );
        hashtag.setFirstUsed( hashtagDto.getFirstUsed() );
        hashtag.setLastUsed( hashtagDto.getLastUsed() );

        return hashtag;
    }

    @Override
    public List<HashtagDto> entitiesToResponseDtos(List<Hashtag> hashtags) {
        if ( hashtags == null ) {
            return null;
        }

        List<HashtagDto> list = new ArrayList<HashtagDto>( hashtags.size() );
        for ( Hashtag hashtag : hashtags ) {
            list.add( hashtagToHashtagDto( hashtag ) );
        }

        return list;
    }

    protected HashtagDto hashtagToHashtagDto(Hashtag hashtag) {
        if ( hashtag == null ) {
            return null;
        }

        HashtagDto hashtagDto = new HashtagDto();

        hashtagDto.setLabel( hashtag.getLabel() );
        hashtagDto.setFirstUsed( hashtag.getFirstUsed() );
        hashtagDto.setLastUsed( hashtag.getLastUsed() );

        return hashtagDto;
    }
}
