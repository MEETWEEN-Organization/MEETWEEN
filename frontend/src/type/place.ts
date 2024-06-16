export interface LatLngType {
  x: string;
  y: string;
  address_name: string;
}

export type AddressType = {
  key: number;
} & LatLngType;

export interface KeywordResultType extends AddressType {
  place_name: string;
  place_url: string;
}
