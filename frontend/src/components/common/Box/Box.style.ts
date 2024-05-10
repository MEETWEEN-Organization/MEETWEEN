import { css } from '@emotion/react';

export interface BoxStyleProps {
  position?: 'static' | 'relative' | 'absolute' | 'fixed' | 'sticky' | 'inherit';
  width?: string;
  height?: string;
  margin?: string;
  marginRight?: string;
  marginTop?: string;
  marginLeft?: string;
  marginBottom?: string;
  padding?: string;
  paddingTop?: string;
  paddingRight?: string;
  paddingBottom?: string;
  paddingLeft?: string;
  border?: string;
  borderRadius?: string;
  borderColor?: string;
  boxShadow?: string;
  backgroundColor?: string;
  color?: string;
}

export const getBoxStyle = ({
  position = 'static',
  width = '',
  height = '',
  margin = '0',
  marginRight = '',
  marginTop = '',
  marginLeft = '',
  marginBottom = '',
  padding = '',
  paddingTop = '',
  paddingRight = '',
  paddingBottom = '',
  paddingLeft = '',
  border = '',
  borderRadius = '',
  borderColor = '',
  boxShadow = '',
  backgroundColor = '',
  color = '',
}: BoxStyleProps) =>
  css({
    position,
    width,
    height,
    margin,
    marginRight,
    marginTop,
    marginLeft,
    marginBottom,
    padding,
    paddingTop,
    paddingRight,
    paddingBottom,
    paddingLeft,
    border,
    borderRadius,
    borderColor,
    boxShadow: boxShadow,
    backgroundColor,
    color,
  });
