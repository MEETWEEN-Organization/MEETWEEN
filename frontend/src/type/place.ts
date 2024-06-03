export interface AddressType {
  x: string;
  y: string;
  address_name: string;
}

export type LatLngType = {
  key: number;
} & Omit<AddressType, 'address_name'>;

export interface KeywordResultType extends AddressType {
  place_name: string;
  place_url: string;
}
