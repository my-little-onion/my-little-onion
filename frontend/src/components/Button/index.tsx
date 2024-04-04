import styled from '@emotion/styled';
import { ReactNode } from 'react';

import theme from '@/styles/theme';

const lightFontColors: string[] = [
  theme.color.black,
  theme.color.gray,
  theme.color.blue,
  theme.color.green,
  theme.color.red,
];

const ButtonContent = styled.button`
  height: 40px;
  border: none;
  cursor: pointer;
  border-radius: 24px;
  margin: 0;
  position: relative;
  background-color: ${(prop) => prop.color};
  color: ${(prop) =>
    lightFontColors.includes(prop.color ?? 'none')
      ? theme.color.white
      : theme.color.black};
  font-size: ${theme.fontSize.medium};
`;

const LargeButton = styled(ButtonContent)`
  width: 300px;
`;

const MediumButton = styled(ButtonContent)`
  width: 240px;
`;

const SmallButton = styled(ButtonContent)`
  width: auto;
  padding: 0 20px;
`;

const SvgButton = styled(ButtonContent)`
  border-radius: 0;
  background-color: transparent;
  margin: 0;
`;

interface ButtonProps {
  type: 'submit' | 'reset' | 'button';
  children?: ReactNode;
  size?: 'small' | 'medium' | 'large';
  color?: string;
  disabled?: boolean;
  onClick?: () => void;
  svg?: ReactNode;
}

const Button = ({
  children,
  disabled,
  onClick,
  size,
  color,
  type,
  svg,
}: ButtonProps) => {
  if (svg) {
    return <SvgButton onClick={onClick}>{svg}</SvgButton>;
  }

  if (size === 'small') {
    return (
      <SmallButton
        type={type}
        color={color}
        disabled={disabled}
        onClick={onClick}
      >
        {children}
      </SmallButton>
    );
  }

  if (size === 'medium') {
    return (
      <MediumButton
        type={type}
        color={color}
        disabled={disabled}
        onClick={onClick}
      >
        {children}
      </MediumButton>
    );
  }

  return (
    <LargeButton
      type={type}
      color={color}
      disabled={disabled}
      onClick={onClick}
    >
      {children}
    </LargeButton>
  );
};

export default Button;
