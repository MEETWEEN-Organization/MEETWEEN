import "@emotion/react";
import { Theme } from "./theme";

interface IText {
  [key: string]: {
    fontSize: string;
    lineHeight: string;
  };
}

declare module "@emotion/react" {
  export interface Theme {
    color: { [key: string]: string };
    text: IText;
    heading: IText;
    borderRadius: { [key: string]: string };
    boxShadow: { [key: string]: string };
    margin: { [key: string]: string };
  }
}
