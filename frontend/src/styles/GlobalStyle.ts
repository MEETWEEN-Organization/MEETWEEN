import { css } from "@emotion/react";
import { Theme } from "@styles/theme/theme";

export const GlobalStyle = css({
  "*": {
    padding: 0,
    margin: 0,
    boxSizing: "border-box",
  },

  "ul, ol, li": {
    listStyle: "none",
  },

  "html, body": {
    fontFamily: `system-ui, -apple-system, BlinkMacSystemFont, 'Open Sans', 'Helvetica Neue'`,
    fontSize: "18px",
  },

  a: {
    textDecoration: "none",
    color: Theme.color.blue700,
  },
});
