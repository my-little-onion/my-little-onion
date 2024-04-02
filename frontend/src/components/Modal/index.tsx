import { ReactNode, useRef } from 'react';
import styled from '@emotion/styled';

import theme from '@/styles/theme';
import useOnClickOutside from '@/hooks/useOnClickOutside';
import { IconClose } from '#/svgs';

import Button from '@/components/Button';

const ButtonWrapper = styled.div`
  position: absolute;
  top: 20px;
  right: 20px;
`;

const ModalOverlay = styled.div`
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  z-index: ${theme.zIndex['1']};
`;

const ModalWrapper = styled.div`
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: fixed;
  z-index: ${theme.zIndex['2']};
`;

const ModalContent = styled(ModalWrapper)`
  width: 20%;
  height: 50svh;
  display: flex;
  justify-content: center;

  background-color: white;
  border-radius: 20px;
  position: relative;
  z-index: ${theme.zIndex['2']};
`;

interface ModalProps {
  onClose: () => void;
  isOpen: boolean;
  children: ReactNode;
}

const Modal = ({ onClose, isOpen, children }: ModalProps) => {
  const ref = useRef<HTMLDivElement>(null);

  useOnClickOutside(ref, () => onClose());

  if (!isOpen) return null;

  return (
    <>
      <ModalOverlay />
      <ModalWrapper>
        <ModalContent>
          <ButtonWrapper>
            <Button
              type='button'
              onClick={onClose}
              svg={<IconClose width={25} height={25} />}
            />
          </ButtonWrapper>
          {children}
        </ModalContent>
      </ModalWrapper>
    </>
  );
};

export default Modal;
