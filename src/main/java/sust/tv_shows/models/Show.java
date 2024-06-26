package sust.tv_shows.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shows")
public class Show {
  @Id
  @GeneratedValue
  long id;

  @Column(nullable = false)
  String title;

  @Column(nullable = false)
  String release_date;

  @Column
  String description;

  String networkName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "network_id", referencedColumnName = "id")
  Network network;

}
