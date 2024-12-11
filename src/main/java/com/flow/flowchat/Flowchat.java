package com.flow.flowchat;




import com.flow.base.BaseEntity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flowchat extends BaseEntity {
	
	private static final long serialVersionUID = -4240411914240299624L;
	
	@ElementCollection
    private List<String> nodes;
    
	@ElementCollection
    private List<String> edges;

}
