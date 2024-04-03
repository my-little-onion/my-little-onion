import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import theme from '@/styles/theme';
import { voiceType } from '@/types/onion';
import useModal from '@/hooks/useModal';
import { deleteOnion } from '@/services/onion';

import Button from '@/components/Button';

interface DeleteOnionButtonProps {
  onionId: number;
}

const DeleteOnionButton = ({ onionId }: DeleteOnionButtonProps) => {
  const [voices, setVoices] = useState<voiceType[]>([]);
  const [isMounted, setIsMounted] = useState<boolean>(false);
  const navigate = useNavigate();
  const { Modal, openModal } = useModal();

  const handleDeleteClick = async () => {
    const voiceData = await deleteOnion(onionId);
    setVoices(voiceData.data);
    openModal();
  };

  useEffect(() => {
    if (!isMounted) {
      setIsMounted(true);
      return;
    }
    navigate('/choose');
  }, [Modal]);

  return (
    <>
      <Button
        type='button'
        size='medium'
        color={theme.color.red}
        onClick={handleDeleteClick}
      >
        양파 까기
      </Button>
      <Modal>
        <div>내가 양파에게 했던 말</div>
        {voices.map((voice) => (
          <div key={voice.voice}>{voice.voice}</div>
        ))}
      </Modal>
    </>
  );
};

export default DeleteOnionButton;
