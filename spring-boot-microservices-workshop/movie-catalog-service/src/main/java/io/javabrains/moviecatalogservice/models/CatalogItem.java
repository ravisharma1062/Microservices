package io.javabrains.moviecatalogservice.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {
    @ApiModelProperty(notes = "Name of the catalog", required = true)
    private String name;
    @ApiModelProperty(notes = "Description of the catalog", required = true)
    private String desc;
    @ApiModelProperty(notes = "Rating for the item in catalog", required = true)
    private int rating;
}
