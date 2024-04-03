import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import ModalContent from '@/components/Modal';

const useModal = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const navigate = useNavigate();

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = (isDelete?: boolean) => {
    setIsOpen(false);
    if (isDelete) navigate('/choose');
  };

  interface ModalProps {
    children: React.ReactNode;
    isDelete?: boolean;
  }

  const Modal = ({ children, isDelete }: ModalProps) => {
    return (
      <ModalContent onClose={() => closeModal(isDelete)} isOpen={isOpen}>
        {children}
      </ModalContent>
    );
  };

  return { Modal, openModal, closeModal };
};

export default useModal;
